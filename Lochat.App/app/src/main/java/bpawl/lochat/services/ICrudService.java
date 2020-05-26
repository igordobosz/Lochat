package bpawl.lochat.services;

public interface ICrudService<T> {
    T Create(T data);
    T Update(T data);
    void Delete(T data);
    T GetById(String id);
}
