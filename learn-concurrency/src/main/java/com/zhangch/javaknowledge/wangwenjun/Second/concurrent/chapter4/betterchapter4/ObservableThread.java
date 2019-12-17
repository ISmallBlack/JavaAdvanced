package com.zhangch.javaknowledge.wangwenjun.Second.concurrent.chapter4.betterchapter4;

/**
 * 功能说明: <br>
 * 系统版本: 1.0 <br>
 * 开发人员: zhanch
 * 开发时间: 2019/12/8<br>
 * <br>
 */
public class ObservableThread <T> extends Thread implements Observable{
    private final TaskLifeCycle<T> lifeCycle;

    private final Task<T> task;

    private Cycle cycle;

    public ObservableThread(Task<T> task){
     this(new TaskLifeCycle.EmptyLifeCycle<T>(),task);
    }

    public ObservableThread(TaskLifeCycle<T> lifeCycle,Task<T> task){
        super();
        if(task == null)
            throw new IllegalArgumentException("Task is required.");
        this.lifeCycle = lifeCycle;
        this.task = task;
    }
    @Override
     public final void run(){
          this.update(Cycle.STARTED,null,null);
          try {
              this.update(Cycle.RUNNING,null,null);
              T result = task.call();
              this.update(Cycle.DONE,result,null);
          }catch (Exception e){
              this.update(Cycle.ERROR,null,e);
          }
     }

     private void update(Cycle cycle,T resule,Exception e){
        this.cycle = cycle;
        if(lifeCycle == null){
            return;
        }
        try{
            switch (cycle){
                case STARTED:
                    this.lifeCycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifeCycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifeCycle.onFinish(currentThread(),resule);
                    break;
                case ERROR:
                    this.lifeCycle.onError(currentThread(),e);
                    break;
            }
        }catch (Exception ex){
           if(cycle == Cycle.ERROR)
               throw ex;
        }
     }


    @Override
    public Cycle getCycle() {
        return this.cycle;
    }
}
