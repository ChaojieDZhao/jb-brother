package basic.design.behavioralpattern.visitor19;

//抽象访问者
public interface Visitor
{
	void visit(UserVIP user);

	void visit(UserOrdinary user);
}