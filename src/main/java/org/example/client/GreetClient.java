package org.example.client;

import java.io.*;
import java.net.Socket;

public class GreetClient implements Closeable{
  private final Socket clientSocket;
  private final PrintWriter out;
  private final BufferedReader in;

  public GreetClient(String ip, int port) throws IOException {
    this.clientSocket = new Socket(ip, port);
    this.out = new PrintWriter(clientSocket.getOutputStream());
    this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  }

  public String sendMessageAndGetResponse(String message) throws IOException {
    out.println(message);
    out.flush();
    return in.readLine();
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
    clientSocket.close();
  }
}
