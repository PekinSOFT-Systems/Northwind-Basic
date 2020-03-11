/*
 * Copyright (C) 2020 PekinSOFT Systems
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * *****************************************************************************
 * *****************************************************************************
 *  Project    :   Northwind-Basic
 *  Class      :   InvalidAccountingDataException.java
 *  Author     :   Sean Carrick
 *  Created    :   Mar 8, 2020 @ 3:28:10 PM
 *  Modified   :   Mar 8, 2020
 *  
 *  Purpose:
 *  
 *  Revision History:
 *  
 *  WHEN          BY                  REASON
 *  ------------  ------------------- ------------------------------------------
 *  Mar 8, 2020  Sean Carrick        Initial creation.
 * *****************************************************************************
 */

package com.pekinsoft.northwind.accounting.exceptions;

/**
 *
 * @author Sean Carrick &lt;sean at pekinsoft dot com&gt;
 * 
 * @version 0.1.0
 * @since 0.1.0
 */
public class InvalidAccountingDataException extends Exception {

    /**
     * Creates a new instance of <code>InvalidAccountingDataException</code> without detail message.
     */
    public InvalidAccountingDataException() {
    }


    /**
     * Constructs an instance of <code>InvalidAccountingDataException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidAccountingDataException(String msg) {
        super(msg);
    }
}
