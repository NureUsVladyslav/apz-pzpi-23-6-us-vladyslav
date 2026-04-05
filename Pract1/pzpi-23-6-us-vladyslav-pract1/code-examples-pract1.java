interface PlayerState {
    void play();
    void pause();
    void stop();
}

class Player {
    private PlayerState state;

    public Player() {
        this.state = new StoppedState(this);
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public void play() {
        this.state.play();
    }

    public void pause() {
        this.state.pause();
    }

    public void stop() {
        this.state.stop();
    }
}

class StoppedState implements PlayerState {
    private Player player;

    public StoppedState(Player player) {
        this.player = player;
    }

    @Override
    public void play() {
        System.out.println("Починаємо відтворення");
        this.player.setState(new PlayingState(this.player));
    }

    @Override
    public void pause() {
        System.out.println("Не можна поставити на паузу, плеєр зупинений");
    }

    @Override
    public void stop() {
        System.out.println("Плеєр вже зупинений");
    }
}

class PlayingState implements PlayerState {
    private Player player;

    public PlayingState(Player player) {
        this.player = player;
    }

    @Override
    public void play() {
        System.out.println("Вже відтворюється");
    }

    @Override
    public void pause() {
        System.out.println("Ставимо на паузу");
        this.player.setState(new PausedState(this.player));
    }

    @Override
    public void stop() {
        System.out.println("Зупиняємо відтворення");
        this.player.setState(new StoppedState(this.player));
    }
}

class PausedState implements PlayerState {
    private Player player;

    public PausedState(Player player) {
        this.player = player;
    }

    @Override
    public void play() {
        System.out.println("Продовжуємо відтворення");
        this.player.setState(new PlayingState(this.player));
    }

    @Override
    public void pause() {
        System.out.println("Плеєр вже на паузі");
    }

    @Override
    public void stop() {
        System.out.println("Зупиняємо відтворення з паузи");
        this.player.setState(new StoppedState(this.player));
    }
}

public class Main {
    public static void main(String[] args) {
        Player player = new Player();

        player.play();
        player.play();
        player.pause();
        player.pause();
        player.play();
        player.stop();
        player.stop();
    }
}