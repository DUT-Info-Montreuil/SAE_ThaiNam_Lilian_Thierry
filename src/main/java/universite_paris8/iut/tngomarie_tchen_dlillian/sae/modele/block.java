/*package universite_paris8.iut.tngomarie_tchen_dlillian.sae.modele;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

    public class block extends Pane implements Updatable{
        private double thickness;
        private Rectangle leftBar;
        private Rectangle rightBar;
        private Rectangle upBar;
        private Rectangle downBar;
        private Node leftNode;
        private Node rightNode;
        private Node upNode;
        private Node downNode;
        private double leftPushMagnitude;
        private double rightPushMagnitude;
        private double upPushMagnitude;
        private double downPushMagnitude;



        public block(double width, double height) {
            thickness = 5;
            leftBar = new Rectangle(thickness, height);
            rightBar = new Rectangle(thickness, height);
            upBar = new Rectangle(width, thickness);
            downBar = new Rectangle(width, thickness);

            rightBar.setTranslateX(width-thickness);
            downBar.setTranslateY(height-thickness);

            leftBar.setFill(Color.LIGHTBLUE);
            rightBar.setFill(Color.LIGHTBLUE);

            upBar.setFill(Color.CHARTREUSE);
            downBar.setFill(Color.RED);

            super.getChildren().addAll(leftBar, rightBar, upBar, downBar);
        }


        public block(Image image) {
            thickness = 5;
            leftBar = new Rectangle(thickness, image.getHeight());
            rightBar = new Rectangle(thickness, image.getHeight());
            upBar = new Rectangle(image.getWidth(), thickness);
            downBar = new Rectangle(image.getWidth(), thickness);

            rightBar.setTranslateX(image.getWidth()-thickness);
            downBar.setTranslateY(image.getHeight()-thickness);

            leftBar.setFill(Color.LIGHTBLUE);
            rightBar.setFill(Color.LIGHTBLUE);

            upBar.setFill(Color.CHARTREUSE);
            downBar.setFill(Color.RED);

            super.getChildren().addAll(new ImageView(image),leftBar, rightBar, upBar, downBar);
        }


        public void visibleBars(boolean hide) {
            leftBar.setVisible(hide);
            rightBar.setVisible(hide);
            upBar.setVisible(hide);
            downBar.setVisible(hide);
        }

        public void buildLeftCollisionWith(Node leftNode, double leftPushMagnitude) {
            this.leftNode = leftNode;
            this.leftPushMagnitude = leftPushMagnitude;
        }

        public void buildRightCollisionWith(Node rightNode, double rightPushMagnitude) {
            this.rightNode = rightNode;
            this.rightPushMagnitude = rightPushMagnitude;
        }

        public void buildUpCollisionWith(Node upNode, double upPushMagnitude) {
            this.upNode = upNode;
            this.upPushMagnitude = upPushMagnitude;
        }

        public void buildDownCollisionWith(Node downNode, double downPushMagnitude) {
            this.downNode = downNode;
            this.downPushMagnitude = downPushMagnitude;
        }

        private void pushX(Node node, Rectangle bar, double magnitude) {
            Bounds nodeBounds = node.localToScene(node.getBoundsInLocal());
            Bounds barBounds = bar.localToScene(bar.getBoundsInLocal());
            if(nodeBounds.intersects(barBounds)) {
                node.setTranslateX(node.getTranslateX() + magnitude);
            }
        }

        private void pushY(Node node, Rectangle bar, double magnitude) {
            Bounds nodeBounds = node.localToScene(node.getBoundsInLocal());
            Bounds barBounds = bar.localToScene(bar.getBoundsInLocal());
            if(nodeBounds.intersects(barBounds)) {
                node.setTranslateY(node.getTranslateY() + magnitude);
            }
        }

        @Override
        public void update() {
            if(leftNode != null) {
                pushX(leftNode, leftBar, -leftPushMagnitude);
            }
            if(rightNode != null) {
                pushX(rightNode, rightBar, rightPushMagnitude);
            }

            if(upNode != null) {
                pushY(upNode, upBar, -upPushMagnitude);
            }

            if(downNode != null) {
                pushY(downNode, downBar, downPushMagnitude);
            }
        }



    }

}
*/