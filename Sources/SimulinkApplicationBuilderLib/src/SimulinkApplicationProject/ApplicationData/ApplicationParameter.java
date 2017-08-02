/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulinkApplicationProject.ApplicationData;

import SimulinkApplicationProject.AbstractObjects.ApplicationDataObject;

/**
 *
 * @author braul
 */
public class ApplicationParameter extends ApplicationDataObject
{
    private final int SizeX;

    private final int SizeY;
    
    private final int SizeZ;
    
    public ApplicationParameter()
    {
        SizeX = 1;
        SizeY = 1;
        SizeZ = 1;
    }

    public ApplicationParameter(int SizeX, int SizeY, int SizeZ, String Name, String DataType, double Min, double Max, double Initial, String Unit, String Description)
    {
        super(Name, DataType, Min, Max, Initial, Unit, Description);
        this.SizeX = SizeX;
        this.SizeY = SizeY;
        this.SizeZ = SizeZ;
    }
}
