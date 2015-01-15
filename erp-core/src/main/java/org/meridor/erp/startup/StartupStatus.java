package org.meridor.erp.startup;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public final class StartupStatus {

    private static boolean hasMoreTasks = true;

    private static final Queue<BeanStatus> tasksQueue = new LinkedList<>();

    public static void addTask(BeanStatus task) {
        synchronized (StartupStatus.class) {
            tasksQueue.add(task);
        }
    }

    public static boolean hasMoreTasks() {
        return hasMoreTasks || tasksQueue.size() > 0;
    }

    public static void setHasMoreTasks(boolean hasMoreTasks) {
        StartupStatus.hasMoreTasks = hasMoreTasks;
    }

    public static Optional<BeanStatus> getNextTask() {
        synchronized (StartupStatus.class) {
            return Optional.ofNullable(tasksQueue.poll());
        }
    }

}
