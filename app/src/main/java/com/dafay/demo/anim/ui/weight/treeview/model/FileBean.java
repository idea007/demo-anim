package com.dafay.demo.anim.ui.weight.treeview.model;


import com.dafay.demo.anim.ui.weight.treeview.TreeNodeId;
import com.dafay.demo.anim.ui.weight.treeview.TreeNodeLabel;
import com.dafay.demo.anim.ui.weight.treeview.TreeNodePid;

public class FileBean
{
	@TreeNodeId
	private int _id;

	@TreeNodePid
	private int parentId;

	@TreeNodeLabel
	private String name;

	private long length;
	private String desc;

	public FileBean(int _id, int parentId, String name)
	{
		super();
		this._id = _id;
		this.parentId = parentId;
		this.name = name;
	}

}
