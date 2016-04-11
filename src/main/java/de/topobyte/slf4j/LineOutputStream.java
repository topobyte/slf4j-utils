// Copyright 2016 Sebastian Kuerten
//
// This file is part of slf4j-utils.
//
// slf4j-utils is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// slf4j-utils is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with slf4j-utils. If not, see <http://www.gnu.org/licenses/>.

package de.topobyte.slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * An {@link OutputStream} that captures lines of text input and forwards them
 * to a {@link LineListener}. It internally buffers bytes until a newline is
 * encountered and once that happens constructs a {@link String} from the
 * buffered bytes and supplies it to the listener.
 * 
 * @author Sebastian Kuerten
 */
public class LineOutputStream extends OutputStream
{

	private LineListener listener;

	private State state = State.NORMAL;
	private ByteArrayOutputStream buffer = new ByteArrayOutputStream();

	public LineOutputStream(LineListener listener)
	{
		this.listener = listener;
	}

	private enum State {
		NORMAL,
		R,
	}

	@Override
	public void write(int b) throws IOException
	{
		switch (state) {
		case NORMAL: {
			if (b == '\r') {
				state = State.R;
			} else if (b == '\n') {
				newLine();
			} else {
				buffer.write(b);
			}
			break;
		}
		case R: {
			if (b == '\n') {
				newLine();
				state = State.NORMAL;
			} else if (b == '\r') {
				// stay in the R state, but write one '\r' to the buffer
				buffer.write(b);
			} else {
				buffer.write(b);
				state = State.NORMAL;
			}
			break;
		}
		}
	}

	private void newLine()
	{
		byte[] bytes = buffer.toByteArray();
		String line = new String(bytes);
		listener.line(line);
		buffer.reset();
	}

}
