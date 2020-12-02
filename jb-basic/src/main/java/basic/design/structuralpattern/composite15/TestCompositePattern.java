package basic.design.structuralpattern.composite15;

import java.util.Iterator;

/**
 * @describe 组合模式有时又叫部分-整体模式在处理类似树形结构的问题时比较方便
 */
public class TestCompositePattern
{
	public static void main(String args[])
	{
		Component root = new Folder("root");
		Component folder1 = new Folder("java");
		Component folder2 = new Folder("c++");
		Component folder3 = new Folder("c#");
		Component file1 = new File("info.txt");
		root.addFolder(folder1).addFolder(folder2).addFolder(folder3).addFile(file1);
		folder1.addFile(new File("info.java"));
		Iterator<Component> iterator = root.iterator();
		while (iterator.hasNext())
		{
			Component component = iterator.next();
			if (component instanceof Folder)
			{
				System.out.print("folder：");
			}
			else
			{
				System.out.print("file：");
			}
			component.display();
		}
	}
}