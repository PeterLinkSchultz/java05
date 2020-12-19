package course.logger;

public interface Logger<T> {
    void save(T object, int toDay);
    void saveQueueSize(int size);
    void print();
}
