package com.xufei.system.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TreeNode implements Serializable {

    private Long id;
    private String label;
    private List<TreeNode> children = new ArrayList<>();

    private Long parentId;
    private Long siteId;

//    private boolean checked;
//    用户额外菜单权限的控制,0是加权限,1是减权限
//    private int extra;

    public TreeNode(Long id, String label, Long parentId, Long siteId) {
        this.id = id;
        this.label = label;
        this.parentId = parentId;
        this.siteId = siteId;
    }
}
