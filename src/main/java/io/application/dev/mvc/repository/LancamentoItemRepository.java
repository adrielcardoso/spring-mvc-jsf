package io.application.dev.mvc.repository;

import io.application.dev.mvc.model.LancamentoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoItemRepository extends JpaRepository<LancamentoItem, Long>
{
}
