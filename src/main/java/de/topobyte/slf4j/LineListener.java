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

/**
 * A callback interface for use with the {@link LineOutputStream}. The
 * {@link #line(String)} method will be executed for each line of input.
 * 
 * @author Sebastian Kuerten
 */
public interface LineListener
{

	public void line(String line);

}
