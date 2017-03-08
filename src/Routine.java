public abstract class Routine {

  public enum RoutineState {
    Success,
    Failure,
    Running,
    Waiting
  }

  protected RoutineState state;

  protected Routine() {
    state = RoutineState.Waiting;
  }

  public void start() {
    this.state = RoutineState.Running;
  }

  public abstract void reset();

  public abstract void act(GameObject droid, Room board);

  protected void succeed() {
    this.state = RoutineState.Success;
  }

  protected void fail() {
    this.state = RoutineState.Failure;
  }

  public boolean isSuccess() {
    return state.equals(RoutineState.Success);
  }

  public boolean isFailure() {
    return state.equals(RoutineState.Failure);
  }

  public boolean isRunning() {
    return state.equals(RoutineState.Running);
  }

  public RoutineState getState() {
    return state;
  }
}
