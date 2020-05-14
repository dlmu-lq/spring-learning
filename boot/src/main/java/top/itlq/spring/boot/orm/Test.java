package top.itlq.spring.boot.orm;

/**
 * 只要是接口都会被MapperScannerConfigurer扫描到，但没有对应的mappedStatement就用不了；
 */
public interface Test {
    void insert();
}
