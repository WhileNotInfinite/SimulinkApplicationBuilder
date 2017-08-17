/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationTypes;

import javafx.scene.control.TreeItem;

/**
 *
 * @author BRAUL
 */
public class ProjectTreeItem extends TreeItem<String>
{
    private Object Tag;

    public ProjectTreeItem(String value)
    {
        super(value);
        this.Tag = null;
    }
    
    public ProjectTreeItem(String value, Object Tag)
    {
        super(value);
        this.Tag = Tag;
    }
    
    public Object getTag()
    {
        return Tag;
    }

    public void setTag(Object Tag)
    {
        this.Tag = Tag;
    }
}
