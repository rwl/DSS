package com.epri.dss.common;

public interface DSSClass {

	public int Get_Active();

	public void Set_Active(int Value);

	public int Get_ElementCount();

	public int Get_First();

	public int Get_Next();

	public String Get_Name();

	public String Get_FirstPropertyName();

	public String Get_NextPropertyName();

	/* Helper routine for building Property strings */
	public void AddProperty(String PropName, int CmdMapIndex,
			String HelpString);

	public void ReallocateElementNameList();

	/* uses global parser */
	public int Edit();

	public int Init(int Handle);

	public int NewObject(String ObjName);

	public boolean SetActive(String Value);

	/* Get address of active obj of this class */
	public Object GetActiveObj();

	/* Find an obj of this class by name */
	public Object Find(String ObjName);

	/* Find property value by string */
	public int PropertyIndex(String Prop);
}
