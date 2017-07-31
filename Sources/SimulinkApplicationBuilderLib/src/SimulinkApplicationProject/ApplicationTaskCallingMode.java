/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulinkApplicationProject;

/**
 *
 * @author braul
 */
public enum ApplicationTaskCallingMode
{
    BACKGROUND(0),
    CYCLIC(1),
    INTERUP(2);

    private final int value;

    private ApplicationTaskCallingMode(int value)
    {
        this.value = value;
    }

}
