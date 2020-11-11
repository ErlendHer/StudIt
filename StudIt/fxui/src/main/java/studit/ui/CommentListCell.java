package studit.ui;

import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import studit.core.mainpage.Comment;
import studit.core.mainpage.CourseItem;
import studit.core.users.User;
import studit.ui.remote.ApiCallException;
import studit.ui.remote.RemoteStuditModelAccess;

public class CommentListCell extends BorderPane {

  RemoteStuditModelAccess remote = new RemoteStuditModelAccess();
  // DiscussionController controller;
  // Linjen over dukket opp som spotbug siden feltet ikke er brukt senere, så
  // kommentert ut inntil videre

  User currentUser;
  CourseItem courseItem;
  private Comment comment;
  private Header header = new Header();
  private Body body = new Body();
  private VBox contentWrapper = new VBox(header, body);

  /**
   * Instantiates a new comment cell.
   *
   * @param comment the comment for this particular cell.
   */
  public CommentListCell(Comment comment, User currentUser, CourseItem courseItem) {
    this.comment = comment;
    this.courseItem = courseItem;
    this.currentUser = currentUser;

    try {
      initialize();
    } catch (Exception e) {
      System.out.println(e);
    }
    setCenter(contentWrapper);
    setMargin(contentWrapper, new Insets(0, 0, 0, 15));
  }

  /**
   * Initialize.
   */
  private void initialize() throws ApiCallException {
    // this.getStyleClass().add("commentListCell");
    header.setTitle(this.comment.getBrukernavn());
    header.setDate(this.comment.getDato());
    body.setComment(this.comment.getKommentar());
    body.setUpvotes(String.valueOf(comment.getUpvotes()));

    // TOOD Foo-method, må endre
    body.button.setOnAction((event) -> {

      List<String> upVoters = this.comment.getUpvoters();

      if (upVoters.contains(this.currentUser.getUsername())) {

        // Gi en visuell feedback
        System.out.println("allerede upvota");

        // fjern UpVote
        /// updateView

      } else {

        try {

          this.remote.upvoteCommentByID(this.courseItem.getFagkode(), this.currentUser.getUsername(),
              this.comment.getUniqueID());

          int id = this.comment.getUniqueID();

          this.comment = this.remote.getCommentById(this.courseItem.getFagkode(), id);

          updateView();

        } catch (Exception e) {

          System.out.println(e);

        }
      }

    });

  }

  /**
   * Updates the commentListCellView.
   */
  public void updateView() {
    header.setTitle(this.comment.getBrukernavn());
    header.setDate(this.comment.getDato());
    body.setComment(this.comment.getKommentar());
    body.setUpvotes(String.valueOf(this.comment.getUpvotes()));

  }

  /**
   * The Class Header.
   */
  static class Header extends AnchorPane {

    private Text title = new Text();
    private Text date = new Text();
    private VBox badge;

    /**
     * Instantiates a new header.
     */
    public Header() {
      setPadding(new Insets(10, 0, 5, 0));

      setLeftAnchor(title, 0.0);
      setBottomAnchor(title, 0.0);
      // ****************TODO***************************************************************************/
      // title.getStyleClass().add("title");

      badge = new VBox(date);
      setRightAnchor(badge, 0.0);
      setBottomAnchor(badge, 5.0);
      // ****************TODO***************************************************************************/
      // badge.getStyleClass().add("state");

      getChildren().addAll(title, badge);
    }

    /**
     * Sets the title.
     *
     * @param title the new title
     */
    private void setTitle(String title) {
      this.title.setText(title);
    }

    /**
     * Sets the date.
     *
     * @param date date
     */
    private void setDate(String date) {
      this.date.setText(date);
    }

  }

  /**
   * The Class Body.
   */
  static class Body extends VBox {

    private Text comment = new Text();
    private Text upvotes = new Text();
    private Button button = new Button("Upvote");

    /**
     * Instantiates a new body.
     */
    public Body() {
      // ****************TODO***************************************************************************/
      // comment.getStyleClass().add("comment");

      VBox.setVgrow(this, Priority.ALWAYS);
      getChildren().addAll(comment, upvotes, button);

    }

    /**
     * Sets the comment.
     *
     * @param comment the new description
     */
    private void setComment(String comment) {
      this.comment.setText(comment);
    }

    /**
     * Sets upvotes.
     *
     * @param amount amount of upvotes
     */
    private void setUpvotes(String upvotes) {
      this.upvotes.setText(upvotes);
    }

    public Button getButton() {
      return this.button;
    }

  }

  // methods for test
  public Body getBody() {
    return this.body;
  }

}
