https://coding-factory.tistory.com/711

프록시 패턴이란

대리인이라는 뜻.


무언가를 대ㅅ 처리한다. 객채를 대행하는 객체를 통해 대상객체에 접근하는 방식

해당 객체가 메모리에 존재하지 않아도 기본적인 정보를 참조하거나 설정할 수 있고


또한 실제 객체의 기능이 반드시 필요한 시점까지 객체의 생성을 미룰 수 있다.

예를들면 용량이 큰 이미지와 글이 같이 있는 문서를 모니터 화면에 띄운다고 가정. 이미지 파일은 용량이 크고 텍스트는 용량이 작아서 텍스트는 빠르게 나타나지만.

그림은 조금 느리게 로딩되는것.

만약 이렇게 처리가 안되고, 이미지와 텍스트가 모두 로딩이 된 후에야 화면이 나온다면. 사용자는 페이지가 로딩될떄까지 의미없이 기다려야함.

그러면 로딩이 먼저되는 텍스트라도 나온ㄴ게 좋지

이런방식을 취하려면 텍스트 처리용프로세서, 그림 처리용 프로세서를 별도로 운영.

이런 구조를 갖도록 설계하는것이 바로 프록시패턴



원격프록시

가상 프록시

보호프록시
주체 클래스에 접근을 제어하기 위한 개게애 대한 접근



Image.java(interface)
```
public interface Image {
   void displayImage();
}
``` 

Real_Image.java
```
public class Real_Image implements Image {

    private String fileName;
    
    public Real_Image(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }
    
    private void loadFromDisk(String fileName) {
        System.out.println("Loading " + fileName);
    }
    
    @Override
    public void displayImage() {
        System.out.println("Displaying " + fileName);
    }
}
 
```

Proxy_Image.java
```
public class Proxy_Image implements Image {
    private Real_Image realImage;
    private String fileName;
    
    public Proxy_Image(String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public void displayImage() {
        if (realImage == null) {
            realImage = new Real_Image(fileName);
        }
        realImage.displayImage();
    }
}
```
 

Proxy_Main.java
```
public class Proxy_Main {
    public static void main(String[] args) {
        Image image1 = new Proxy_Image("test1.png");
        Image image2 = new Proxy_Image("test2.png");
        
        image1.displayImage();
        System.out.println();
        image2.displayImage();
    }
}
```



output

loading test1.png
displaying test1.png

loading test2.png
displaying test2.png
