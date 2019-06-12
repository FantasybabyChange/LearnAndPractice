/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.fantasybaby.algorithm.dfs;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/**
 * 使用Stack 栈
 * 实现深度优先遍历
 * @author reid.liu
 * @date 2019-06-10 18:41
 */
public class PrefixTreeBuildStack {
    /**
     * 字典
     */
    private String[] dictionaries = new String[]{"apple",
            "numerical","adobe","number","cancel","case"} ;

    public  void printByStack(PrefixTreeNode prefixTreeNode){
        Stack<PrefixTreeNode> stack = new Stack();
        stack.push(prefixTreeNode);
        while (!stack.isEmpty()){
            PrefixTreeNode pop = stack.pop();
            Map<String, PrefixTreeNode> sons = pop.getSons();
            if(Objects.nonNull(sons) && sons.size() > 0){
                sons.values().forEach(son-> stack.push(son));
            }else{
                System.out.println(pop.getPrefix()+"-"+pop.getNodeValue());
            }
        }

    }
    @Data
    private class PrefixTreeNode{
        private String nodeName="";
        private String nodeValue="";
        private String prefix="";
        private Boolean lastNode = false;
        /**
         * 使用map更符合前缀树的特点
         */
        private Map<String, PrefixTreeNode> sons = Maps.newHashMap();
    }

    public void buildPrefixTree(PrefixTreeNode parentNode,String lastStr){
        if(lastStr.length() == 0){
            parentNode.setLastNode(true);
//            System.out.println("this word not exist");
            return;
        }
        Map<String, PrefixTreeNode> sons = parentNode.getSons();
        String strValue = lastStr.substring(0,1);
        String resetStr = lastStr.substring(1);

        PrefixTreeNode prefixTreeNode = sons.get(strValue);
        if(Objects.isNull(prefixTreeNode)){
            PrefixTreeNode newPrefixTree = new PrefixTreeNode();
            newPrefixTree.nodeName = "node:"+strValue;
            newPrefixTree.nodeValue = strValue+"";
            newPrefixTree.prefix=parentNode.prefix+parentNode.nodeValue;
            sons.putIfAbsent(strValue,newPrefixTree);
            buildPrefixTree(newPrefixTree,resetStr);
            return;
        }
        buildPrefixTree(prefixTreeNode,resetStr);
    }
    public PrefixTreeNode buildByDictionary(){
        PrefixTreeNode root = new PrefixTreeNode();
        root.setNodeName("root");
        for (String dictionary : dictionaries) {
            buildPrefixTree(root,dictionary);
        }
        return root;
    }
    public static void main(String[] args) {
        PrefixTreeBuildStack prefixTreeBuildRecursion = new PrefixTreeBuildStack();
        PrefixTreeNode prefixTreeNode = prefixTreeBuildRecursion.buildByDictionary();
        prefixTreeBuildRecursion.printByStack(prefixTreeNode);
        /*prefixTreeBuildRecursion.printTree(prefixTreeNode);
        prefixTreeBuildRecursion.searchWord(prefixTreeNode,"ap");*/

    }

}
