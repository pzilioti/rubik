package rubik.play;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class sandbox {

    public static void main(String args[]){


        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello2";
        });

        CompletableFuture<Object> future = CompletableFuture.anyOf(future1, future2);
        try {
            String result = (String) future.get();
            System.out.println(result);
        } catch (InterruptedException  | ExecutionException e) {
            // Handle
        }


    }
}
