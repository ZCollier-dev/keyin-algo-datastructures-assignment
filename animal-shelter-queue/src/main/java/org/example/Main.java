package org.example;

/**
 * An Animal shelter, which holds only dogs and cats, operates on a strictly "first in, first out" bases.
 * People must adopt either the "oldest" (based on arrival time) of  all animals at the shelter,
 * or they can select whether they would prefer a dog or cat
 * (and will receive the oldest animal of that type).
 * They cannot select which specific animal they would like.
 * Create a datastucture to maintain this system and implement operations such as
 * enqueue, dequeueAny, dequeueDog and DequeueCat.
 */

public class Main {
    public static void main(String[] args) {
        Queue queue = new Queue(10);

        // check for empty queue
        queue.dequeueAny(); // none

        // add pet objects to queue
        queue.enqueuePet("Tiny", "Dog"); // first
        queue.enqueuePet("Scruffy", "Dog");
        queue.enqueuePet("Mittens", "Cat");
        queue.enqueuePet("Mini", "Cat");
        queue.enqueuePet("Puffball", "Dog");
        queue.enqueuePet("Flippy", "Cat");
        queue.enqueuePet("Pawprint", "Dog");
        queue.enqueuePet("Manny", "Dog");
        queue.enqueuePet("Tiny", "Cat");
        queue.enqueuePet("Garfield", "Cat"); // last (10)

        // add to queue to trigger overflow
        queue.enqueuePet("Invalid", "Dog"); // overflow

        System.out.println();

        // dequeue
        queue.dequeueAny(); // Should be Tiny the Dog (9)
        // System.out.println(queue);
        queue.dequeueSpecies("Cat"); // Should be Mittens the Cat (8)
        // System.out.println(queue);
        queue.dequeueSpecies("Dog"); // Should be Scruffy the Dog (7)
        // System.out.println(queue);
        queue.dequeueSpecies("Snake"); // Should return nothing
        // System.out.println(queue);
        queue.dequeueAny(); // Should be Mini the Cat (6)
        // System.out.println(queue);
        queue.dequeueSpecies("Cat"); // Should be Flippy the Cat
        // System.out.println(queue);
        queue.dequeueSpecies("Cat"); // Should be Tiny the Cat
        // System.out.println(queue);
        queue.dequeueSpecies("Cat"); // Should be Garfield the Cat
        // System.out.println(queue);
    }
}