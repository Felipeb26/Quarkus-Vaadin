package com.felipes.test.frontend.utils;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.select.Select;
import jakarta.enterprise.context.Dependent;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;

@Slf4j
@Dependent
public class AsyncFillComponent<T, R> {

    public void fillSelect(Function<? super T, ? extends R> map, UI ui, Select<String> select, Function<Void, List<T>> function) {
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.schedule(() -> {
            log.info("DATA QUE ROUDOU {}", LocalDateTime.now());
            try {
                var list = asycnFunction(function).get();
                ui.access(() -> select.setItems((List<String>) list.stream().map(map).toList()));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }, 5, TimeUnit.SECONDS);
    }


    private Future<List<T>> asycnFunction(Function<Void, List<T>> function) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        return executorService.submit(() -> {
            Future<List<T>> future = CompletableFuture.completedFuture(function.apply(null));
            return future.get();
        });
    }

}
