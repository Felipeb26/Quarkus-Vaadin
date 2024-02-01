package com.felipes.test.frontend.views;

import com.felipes.test.backend.domain.entity.TransferenciaEntity;
import com.felipes.test.backend.service.TransferenciaService;
import com.felipes.test.frontend.HeaderLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
@PageTitle("home")
@Route(value = "", layout = HeaderLayout.class)
public class MainView extends VerticalLayout {

    @Inject
    private TransferenciaService transferenciaService;

    MainView() {
    }


    private void delayFunction(Integer delay) {
        var ui = UI.getCurrent();
        log.trace("DATA QUE INICIOU: {}", LocalDateTime.now());
        var scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.schedule(() -> {
            log.trace("DATA QUE ROUDOU: {}", LocalDateTime.now());
            int limit = 1;
            try {
                var contatoEntities = contatos().get();
                ui.access(() -> {
                });
            } catch (Exception e) {
                while (limit <= 3) {
                    limit++;
                    delayFunction(limit);
                }
                log.error(e.getMessage());
            }
        }, delay == null ? 1 : delay, TimeUnit.SECONDS);
    }

    private Future<List<TransferenciaEntity>> contatos() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        return executorService.submit(() -> {
            Future<List<TransferenciaEntity>> future = CompletableFuture.completedFuture(transferenciaService.findAll());
            return future.get();
        });
    }


}
