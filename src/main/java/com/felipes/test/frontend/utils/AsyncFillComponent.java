package com.felipes.test.frontend.utils;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.select.Select;
import jakarta.enterprise.context.Dependent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Dependent
public class AsyncFillComponent<T, R> {

    public void fillSelect(Function<? super T, ? extends R> map, Future<List<T>> future,UI ui, Select<String> select) {
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.schedule(() -> {
            System.out.println("DATA QUE ROUDOU " + LocalDateTime.now());
            try {
                List<T> list = future.get();
                ui.access(() -> select.setItems((List<String>) list.stream().map(map).toList()));
            } catch (Exception e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }, 5, TimeUnit.SECONDS);
    }

//    public void fillSelect(Function<? super T, ? extends R> map, Future<List<T>> future, Select<String> select, Integer delay, TimeUnit timeUnit) {
//        var ui = UI.getCurrent();
//        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutor.schedule(() -> {
//            log.info("DATA QUE ROUDOU " + LocalDateTime.now());
//            try {
//                List<T> list = future.get();
//                ui.access(() -> select.setItems((DataProvider<String, Void>) list.stream().map(map).toList()));
//            } catch (Exception e) {
//                log.error(e.getMessage(), e);
//            }
//        }, delay, timeUnit == null ? TimeUnit.SECONDS : timeUnit);
//    }
}
