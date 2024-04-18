package org.example.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetServer implements Closeable{
  private final ServerSocket serverSocket;
  private final Socket clentSocket;
  private PrintWriter out;
  private BufferedReader in;

  public GreetServer(int port)
      throws IOException {
    this.serverSocket = new ServerSocket(port);
    this.clentSocket = serverSocket.accept();
    start();
  }

  private void start() throws IOException {
    this.out = new PrintWriter(this.clentSocket.getOutputStream());
    this.in = new BufferedReader(new InputStreamReader(clentSocket.getInputStream()));
    String message;
    do {
      message = in.readLine();
      messaging(message);
    } while (!message.equals("bye"));
  }



  private void messaging(String message) {
    if (message.equals("Hello, server")) {
      out.println("Hello client!");
    } else {
      out.println("Unrecognised message");
    }
    out.flush();
  }
  /**
   * Closes this resource, relinquishing any underlying resources.
   * This method is invoked automatically on objects managed by the
   * {@code try}-with-resources statement.
   *
   * @throws Exception if this resource cannot be closed
   * @apiNote While this interface method is declared to throw {@code
   * Exception}, implementers are <em>strongly</em> encouraged to
   * declare concrete implementations of the {@code close} method to
   * throw more specific exceptions, or to throw no exception at all
   * if the close operation cannot fail.
   *
   * <p> Cases where the close operation may fail require careful
   * attention by implementers. It is strongly advised to relinquish
   * the underlying resources and to internally <em>mark</em> the
   * resource as closed, prior to throwing the exception. The {@code
   * close} method is unlikely to be invoked more than once and so
   * this ensures that the resources are released in a timely manner.
   * Furthermore it reduces problems that could arise when the resource
   * wraps, or is wrapped, by another resource.
   *
   * <p><em>Implementers of this interface are also strongly advised
   * to not have the {@code close} method throw {@link
   * InterruptedException}.</em>
   * <p>
   * This exception interacts with a thread's interrupted status,
   * and runtime misbehavior is likely to occur if an {@code
   * InterruptedException} is {@linkplain Throwable#addSuppressed
   * suppressed}.
   * <p>
   * More generally, if it would cause problems for an
   * exception to be suppressed, the {@code AutoCloseable.close}
   * method should not throw it.
   *
   * <p>Note that unlike the {@link Closeable#close close}
   * method of {@link Closeable}, this {@code close} method
   * is <em>not</em> required to be idempotent.  In other words,
   * calling this {@code close} method more than once may have some
   * visible side effect, unlike {@code Closeable.close} which is
   * required to have no effect if called more than once.
   * <p>
   * However, implementers of this interface are strongly encouraged
   * to make their {@code close} methods idempotent.
   */
  @Override
  public void close() throws IOException {
    in.close();
    out.close();
    clentSocket.close();
    serverSocket.close();
  }

  public static void main(String[] args) {
    try(GreetServer server = new GreetServer(7777)) {

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
