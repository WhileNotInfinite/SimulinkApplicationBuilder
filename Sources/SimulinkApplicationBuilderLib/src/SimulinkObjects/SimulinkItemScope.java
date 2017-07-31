/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulinkObjects;

/**
 *
 * @author braul
 */
public enum SimulinkItemScope
{
    AUTO(0),
    EXPORTED(1),
    IMPORTED(2);

    private final int value;

    private SimulinkItemScope(int value)
    {
        this.value = value;
    }
}
