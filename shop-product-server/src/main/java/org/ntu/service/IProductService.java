package org.ntu.service;

import org.ntu.domain.Product;

public interface IProductService {
    public Product findByPid(Long pid);
}
