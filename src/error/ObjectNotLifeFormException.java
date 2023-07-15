package error;

/**
 * Исключение, выбрасываемое при попытке обработать объект, который не является формой жизни.
 */
public class ObjectNotLifeFormException extends Exception {
    /**
     * Конструктор класса ObjectNotLifeFormException.
     *
     * @param msg Сообщение об ошибке
     */
    public ObjectNotLifeFormException(String msg) {
        super(msg);
    }
}
