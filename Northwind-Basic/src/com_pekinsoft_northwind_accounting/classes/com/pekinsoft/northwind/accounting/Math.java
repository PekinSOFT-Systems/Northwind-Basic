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
 *  Class      :   Math.java
 *  Author     :   Sean Carrick
 *  Created    :   Mar 8, 2020 @ 3:23:00 PM
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

package com.pekinsoft.northwind.accounting;

import com.pekinsoft.northwind.accounting.exceptions.InvalidAccountingDataException;
import com.pekinsoft.northwind.basic.Application;
import com.pekinsoft.northwind.utils.Logger;

/**
 *
 * @author Sean Carrick &lt;sean at pekinsoft dot com&gt;
 * 
 * @version 0.1.0
 * @since 0.1.0
 */
public class Math {
    //<editor-fold defaultstate="collapsed" desc="Public Static Constants">
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Private Member Fields">
    Logger log = Application.log;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Static Initializer">
    static {
        
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Intstance Initializer">
    {
        
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
    private Math () {
        /* To make sure this class cannot be instantiated. */
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public Static Methods">
    /**<p>
     * This function is a way of telling how quickly the company can pay its
     * short-term debts.</p>
     * <p>
     * FORMULA:<br><br>
     * Current Assets / Current Liabilities = Current Ratio
     * <p>
     * EXAMPLE:<br><br>
     * 
     * Total Assets: $15,000.00<br>
     * Current Assets: $8,000.00</p>
     * <p>A <em>current asset</em> is cash or something that can easily be 
     * converted into cash, such as accounts receivable and short-term
     * investments.</p>
     * <p>Total Liabilities: $5,000.00<br>
     * Current Liabilities: $2,000.00</p>
     * <p>A <em>current liability</em> is a debt due within the next twelve (12)
     * months. Accounts Payable, credit card balances, and short-term lines of
     * credit are all current liabilities.</p>
     * <p>
     * Based upon the information provided above, the <em>current ratio</em> is
     * 4 ($8,000.00 / $2,000.00 = 4). This would mean that the example company
     * has four times as many current assets as it does current liabilities, 
     * which means that it could pay of the short-term debt four (4) times
     * before running out of cash.</p>
     * <p>
     * Ideally, a company's current ratio should be greater than one (1). 
     * However, if the company's current ratio is too high, it indicates that 
     * the company is not managing its capital efficiently and, as a result, the
     * company's growth could stagnate.</p>
     * 
     * @param currentAssets         Total current assets.
     * @param currentLiabilities    Total current liabilities.
     * @return The current ratio of the company's financial position.
     * @throws InvalidAccountingDataException In the event data is not provided.
     */
    public static double CurrentRatio(double currentAssets, 
            double currentLiabilities) throws InvalidAccountingDataException {
        double[] params = {currentAssets, currentLiabilities};
        Application.log.enter(Math.class.getCanonicalName(), "CurrentRatio", 
                params);
        Application.log.debug("Verifying that data is present...");
        InvalidAccountingDataException ex = null;
        if ( currentAssets == 0.0 ) {
            ex = new InvalidAccountingDataException("No value for current "
                    + "assets was provided.");
            Application.log.handledError(ex, "Values provided:\n\tdouble "
                    + "currentAssets = " + currentAssets + "\n\tdouble "
                    + "currentLiabilities = " + currentLiabilities);
            throw ex;
        }
        if ( currentLiabilities == 0.0 ) {
            ex = new InvalidAccountingDataException("No valud for current "
                    + "liabilities was provided.");
            
            Application.log.handledError(ex, "Values provided:\n\tdouble "
                    + "currentAssets = " + currentAssets + "\n\tdouble "
                    + "currentLiabilities = " + currentLiabilities);
            throw ex;
        }
        
        Application.log.debug("Returning a current ratio of: " + 
                currentAssets / currentLiabilities + " = " + currentAssets +
                " / " + currentLiabilities);
        Application.log.exit(Math.class.getCanonicalName(), "CurrentRatio", 
                currentAssets / currentLiabilities);
        return currentAssets / currentLiabilities;
        
    }
    
    /**
     * Provides the amount of the return on an investment made. This calculation
     * can be used to verify that an investment would return enough of a return
     * to justify making the investment in the first place.
     * 
     * @param profit The expected profit margin
     * @param cost   The initial investment made
     * @return
     * @throws InvalidAccountingDataException In the event that one of the data
     *                                        points is not provided.
     */
    public static double ReturnOnInvestment(double profit, double cost) 
            throws InvalidAccountingDataException {
        // Verify that data has been provided.
        if ( profit == 0.0 ) 
            throw new InvalidAccountingDataException("No profit provided.");
        if ( cost == 0.0 ) 
            throw new InvalidAccountingDataException("No cost provided.");
        
        return profit / cost;
    }
    
    /**
     * This is a more complex formula that is used for calculating the return on
     * an investment. Profit margin is operating profit divided by sales. Asset
     * turnover is calculated as sales divided by average assets. Average assets
     * refers to assets at beginning of the period.
     * @param profit
     * @param asset
     * @return
     * @throws InvalidAccountingDataException 
     */
    public static double DuPontROI(double profit, double asset) 
            throws InvalidAccountingDataException {
        // Verify that data has been provided.
        if ( profit == 0.0 )
            throw new InvalidAccountingDataException("No profit provided.");
        if ( asset == 0.0 )
            throw new InvalidAccountingDataException("No asset provided.");
        return profit * asset;
    }
    
    /**
     * Net income, or the bottom line, is a measurement of the profitability of
     * a company. This important calculation tells you at a glance if you are
     * spending too much money in relation to the revenue coming in. It's 
     * important to note, however, that net income does not equal cash in the
     * bank. Payments on liabilities &ndash; the debts you owe, which appear on
     * the balance sheet &ndash; are not included in the net income equation.
     * Neither are contributions of capital, draws and distributions, or asset
     * acquisition.
     * 
     * @param totalRevenue  The total revenue coming into the company.
     * @param totalExpenses The total expenses being spent by the company.
     * @return              The net income of the company.
     * @throws InvalidAccountingDataException In the event that the data 
     *                      provided is less than or equal to zero.
     */
    public static double NetIncome(double totalRevenue, double totalExpenses)
            throws InvalidAccountingDataException {
        double[] params = {totalRevenue, totalExpenses};
        Application.log.enter(Math.class.getCanonicalName(), "NetIncome", params);
        
        // Create an exception object to use.
        InvalidAccountingDataException ex = null;
        Application.log.debug("Verifying that data was provided...");
        if ( totalRevenue <= 0.0 ) {
            ex = new InvalidAccountingDataException("No 'totalRevenue' value "
                    + "supplied.\n\tdouble totalRevenue = " + totalRevenue);
            Application.log.error(ex, "Invalid Accounting Data Supplied.");
            throw ex;
        }
        if ( totalExpenses <= 0.0 ) {
            ex = new InvalidAccountingDataException("No 'totalExpenses' value "
                    + "supplied.\n\tdouble totalExpenses = " + totalExpenses);
            Application.log.error(ex, "Invalid Accounting Data Supplied.");
            throw ex;
        }
        
        double ret = totalRevenue - totalExpenses;
        Application.log.exit(Math.class.getCanonicalName(), "NetIncome", params, 
                ret);
        return ret;
    }
    
    /**
     * The basic accounting equation, this function tells whether or not the 
     * balance sheet will actually balance.
     * <p>
     * This function tells how healthy your business is at a glance. Let's look
     * at an example to see how this works:</p>
     * <p>
     * Company A has assets (bank account(s), computers, trucks, trailers, etc.)
     * that have a total value of $15,000.00. Furthermore, Company A has total
     * liabilities (credit cards, lines of credit, accounts payable, etc.) that
     * total to $5,000.00. Given these two numbers, the equity of the company 
     * (the total of all of your combined contributions and profits made) is
     * $10,000.00. When this function is called in this situation, it will 
     * return the value {@code true} because everything balances out and the 
     * company is healthy. If, in order to balance the company's books, you are
     * required to continually contribute money to the company from your own
     * bank account, then your company would not be considered healthy.</p>
     * <p>
     * Most equity should be from profits and not from owner contributions.</p>
     * 
     * @param assets        bank balance, vehicles, equipment, etc.
     * @param liabilities   what is owed: credit cards, loans, etc.
     * @param equity        what the company owns outright: profit, contributions.
     * @return              {@code true} if balanced; {@code false} otherwise.
     * @throws InvalidAccountingDataException In the event any data point is
     *                      zero (0) or less than zero.
     */
    public static boolean isBalanced(double assets, double liabilities, 
            double equity) throws InvalidAccountingDataException {
        double[] params = {assets, liabilities, equity};
        Application.log.enter(Math.class.getCanonicalName(), "isBalanced", 
                params);
        InvalidAccountingDataException ex = null;
        Application.log.debug("Verifying that data was provided...");
        if ( assets <= 0.0 ) {
            ex = new InvalidAccountingDataException("No 'assets' value "
                    + "supplied.\n\tdouble assets = " + assets);
            Application.log.error(ex, "Invalid Assets Value Supplied.");
            throw ex;
        }
        if ( liabilities <= 0.0 ) {
            ex = new InvalidAccountingDataException("No 'liabilities' value "
                    + "supplied.\n\tdouble liabilities = " + liabilities);
            Application.log.error(ex, "Invalid Liabilities Value Supplied.");
            throw ex;
        }
        if ( equity <= 0.0 ) {
            ex = new InvalidAccountingDataException("No 'equity' value "
                    + "supplied.\n\tdouble equity = " + equity);
            Application.log.error(ex, "Invalid Equity Value Supplied.");
            throw ex;
        }
        
        boolean ret = assets == (liabilities + equity);
        Application.log.exit(Math.class.getCanonicalName(), "isBalanced", 
                params, ret);
        return ret;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public Instance Methods">
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Private Instance Methods">
    
    //</editor-fold>

}
