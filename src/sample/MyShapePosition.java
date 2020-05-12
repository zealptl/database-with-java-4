package sample;

public interface MyShapePosition extends MyPoint {
    MyRectangle getBoundingBox();
    boolean doOverlap(MyShape shape2);
    boolean doMyRectangleOverlap(MyRectangle rect1, MyRectangle rect2);
}
