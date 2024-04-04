package io.student.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MibNode {

    private String name;
    private List<MibNode> children;

    public MibNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<MibNode> getChildren() {
        return children;
    }

    public void addChild(MibNode child) {
        children.add(child);
    }


}
