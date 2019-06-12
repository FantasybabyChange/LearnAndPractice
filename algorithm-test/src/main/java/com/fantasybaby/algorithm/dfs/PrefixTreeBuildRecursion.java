/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.fantasybaby.algorithm.dfs;

import lombok.Data;

import java.util.Map;
import java.util.Objects;

/**
 * 使用递归
 * 根据字典构建前缀树
 * @author reid.liu
 * @date 2019-06-10 18:41
 */
public class PrefixTreeBuildRecursion {
    /**
     * 字典
     */
    private String[] dictionaries = new String[]{"apple",
            "numerical","adobe","number","cancel","case"
    } ;

    public void buildPrefixTree(PrefixTreeNode parentNode,String lastStr){
        if(lastStr.length() == 0){
            parentNode.setLastNode(true);
//            System.out.println("this word not exist");
            return;
        }
        PrefixTreeNode[] childNodes = parentNode.getChildNodes();
        char strValue = lastStr.charAt(0);
        String resetStr = lastStr.substring(1);
        if(Objects.isNull(childNodes) || childNodes.length == 0){
            /**
             * 这么实现深度遍历会很麻烦
             */
            childNodes = new PrefixTreeNode[26];
            int index = calcIndex(strValue);
            generateNewChild(childNodes, strValue, index);
            parentNode.setChildNodes(childNodes);
            buildPrefixTree(childNodes[index],resetStr);
        }else{
            int index = calcIndex(strValue);
            PrefixTreeNode indexChild = childNodes[index];
            if(Objects.nonNull(indexChild)){
                buildPrefixTree(indexChild,resetStr);
            }else{
                generateNewChild(childNodes, strValue, index);
                parentNode.setChildNodes(childNodes);
                buildPrefixTree(childNodes[index],resetStr);
            }

           /* for (PrefixTreeNode childNode : childNodes) {
                String nodeValue = childNode.getNodeValue();

                if(strValue.equals(nodeValue)){
                    return;
                }
            }*/
            /**
             * 使用索引默认有26个容量
             * 每次扩容1 性能不怎么样
             */
//            PrefixTreeNode[] prefixTreeNodes = Arrays.copyOf(childNodes, childNodes.length + 1);
//            prefixTreeNodes[prefixTreeNodes.length - 1] = new PrefixTreeNode();
//            prefixTreeNodes[prefixTreeNodes.length - 1].nodeName = "node:"+strValue;
//            prefixTreeNodes[prefixTreeNodes.length - 1].nodeValue = strValue;
//            parentNode.setChildNodes(prefixTreeNodes);
//            buildPrefixTree(prefixTreeNodes[prefixTreeNodes.length - 1],resetStr);
        }
    }

    private void searchWord(PrefixTreeNode node,String str){
        if(str.length() == 0){
            if(node.getLastNode()){
                System.out.println("单词存在");
            }else{
                System.out.println("单词不存在");
            }
            return;
        }
        PrefixTreeNode[] childNodes = node.getChildNodes();
        if(Objects.isNull(childNodes) || childNodes.length == 0){
            System.out.println("单词不存在");
            return;
        }
        char word = str.charAt(0);
        int index = calcIndex(word);
        PrefixTreeNode childNode = childNodes[index];
        if(Objects.isNull(childNode)){
            System.out.println("单词不存在");
            return;
        }else{
            searchWord(childNode,str.substring(1));
            return;
        }

    }
    private void generateNewChild(PrefixTreeNode[] childNodes, char strValue, int index) {
        childNodes[index]  = new PrefixTreeNode();
        childNodes[index].nodeName = "node:"+strValue;
        childNodes[index].nodeValue = strValue+"";
    }

    /**
     * 前缀树构建
     */
    @Data
    public class PrefixTreeNode{
        private String nodeName;
        private String nodeValue;
        private Boolean lastNode = false;
        /**
         * 这个数据结构的问题在于查询很快,遍历很慢
         */
        private PrefixTreeNode[] childNodes;
        /**
         * 使用map更符合前缀树的特点
         */
        private Map<String,PrefixTreeNode> sons;
    }

    public PrefixTreeNode buildByDictionary(){
        PrefixTreeNode root = new PrefixTreeNode();
        root.setNodeName("root");
        for (String dictionary : dictionaries) {
            buildPrefixTree(root,dictionary);
        }
        return root;
    }
    public void printTree(PrefixTreeNode prefixTreeNode){
        PrefixTreeNode[] childNodes = prefixTreeNode.childNodes;
        if(Objects.isNull(childNodes) || childNodes.length == 0){
            return;
        }
        for (PrefixTreeNode childNode : childNodes) {
            if(Objects.isNull(childNode)){
                continue;
            }
            System.out.println(childNode.getNodeName() + "---" + childNode.getNodeValue());
            printTree(childNode);
        }
    }

    /**
     * 创建索引
     * @param word
     * @return
     */
    private int calcIndex(char word){
        return word - 'a';
    }

    public static void main(String[] args) {
        PrefixTreeBuildRecursion prefixTreeBuildRecursion = new PrefixTreeBuildRecursion();
        PrefixTreeNode prefixTreeNode = prefixTreeBuildRecursion.buildByDictionary();
        prefixTreeBuildRecursion.printTree(prefixTreeNode);
        prefixTreeBuildRecursion.searchWord(prefixTreeNode,"ap");
    }

}
