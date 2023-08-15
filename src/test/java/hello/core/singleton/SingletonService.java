package hello.core.singleton;

public class SingletonService {
  private static final SingletonService instance = new SingletonService(); // point: static

  // 갖다 쓸 때는 이걸로
  public static SingletonService getInstance() {
    return instance;
  }

  private SingletonService() {
  }

  public void logic() {
    System.out.println("싱글톤 객체 로직 호출");
  }

}
