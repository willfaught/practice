public interface Enumerator<E>
{
    boolean more();
    E next();
}