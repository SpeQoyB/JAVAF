package university.interfaces;

/**
 * Позначає об'єкти, за які потрібно здійснювати оплату.
 */
public interface Payable {

    /**
     * Встановлює статус оплати.
     */
    void setPaid(boolean paid);

    /**
     * Повертає true, якщо оплата виконана.
     */
    boolean isPaid();
}
