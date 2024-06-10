package org.example.icon;

public class Icon {
    String composite;
    String leaf;

    public Icon(char compositeIcon, char leafIcon ){
        this.leaf = String.valueOf(leafIcon);
        this.composite = String.valueOf(compositeIcon);
    }

    public Icon(String compositeIcon, String leafIcon ){
        this.leaf = leafIcon;
        this.composite = compositeIcon;
    }

    public String getComposite() {
        return composite;
    }

    public String getLeaf() {
        return leaf;
    }
}
