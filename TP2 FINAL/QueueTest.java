package queue;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {

  @Test
  public void test01QueueShouldBeEmptyWhenCreated() {
    assertTrue( new Queue().isEmpty() );
  }

  @Test public void test02AddElementsToTheQueue() {
    assertFalse( new Queue().add( "Something" ).isEmpty() );
  }

  @Test public void test03AddedElementsIsAtHead() {
    assertEquals( "Something", new Queue().add( "Something" ).head() );
  }

  @Test public void test04TakeRemovesElementsFromTheQueue() {
    Queue queue = new Queue().add( "Something" );
    queue.take();

    assertTrue( queue.isEmpty() );
  }

  @Test public void test05TakeReturnsLastAddedObject() {
    Queue queue = new Queue();
    String addedObject = "Something";
    queue.add( addedObject );

    assertEquals( addedObject, queue.take() );
  }

  @Test public void test06QueueBehavesFIFO() {
    Queue queue = new Queue();
    String firstAddedObject = "First";
    String secondAddedObject = "Second";

    queue.add( firstAddedObject );
    queue.add( secondAddedObject );

    assertEquals( queue.take(), firstAddedObject );
    assertEquals( queue.take(), secondAddedObject );
    assertTrue( queue.isEmpty() );
  }

  @Test public void test07HeadReturnsFirstAddedObject() {
    Queue queue = new Queue();
    String firstAddedObject = "First";

    queue.add( firstAddedObject );
    queue.add( "Second" );

    assertEquals( queue.head(), firstAddedObject );
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
    Queue queue = new Queue();
    queue.add( "Something" );
    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, new Queue().add( "First" ).add( "Second" ).size() );
  }

  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    assertTrue(assertThrows( Error.class, () -> new Queue().take()).getMessage().equals( "Queue is empty" ));
  }

  @Test public void test09CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = new Queue();
    queue.add( "Something" );
    queue.take();
    assertTrue(assertThrows( Error.class, () -> queue.take()).getMessage().equals( "Queue is empty" ));
  }

  @Test public void test10CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    assertTrue(assertThrows(Error.class, () -> new Queue().head()).getMessage().equals("Queue is empty"));
  }
}
