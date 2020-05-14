package designPatterns;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/5/8
 * @Time:10:46
 */
public class AbstractFactory {

    public static void main(String[] args) {
        IPhoneFactor iPhoneFactor = new IPhoneFactor();
        iPhoneFactor.getVideo().run();
        iPhoneFactor.getMusic().play();
        MiPhoneFactor miPhoneFactor = new MiPhoneFactor();
        miPhoneFactor.getVideo().run();
        miPhoneFactor.getMusic().play();
    }

}

abstract class MyAbstractFactory{
    abstract Video getVideo();
    abstract Music getMusic();
}

class MiPhoneFactor extends MyAbstractFactory{
    @Override
    Video getVideo() {
        return new MiPhoneVideo();
    }

    @Override
    Music getMusic() {
        return new MiPhoneMusic();
    }
}

class IPhoneFactor extends MyAbstractFactory{
    @Override
    Video getVideo() {
        return new IPhoneVideo();
    }

    @Override
    Music getMusic() {
        return new IPhonMusic();
    }
}

interface Video{
    void run();
}

class IPhoneVideo implements Video{
    @Override
    public void run() {
        System.out.println("苹果手机播放视频中");
    }
}

class MiPhoneVideo implements Video{
    @Override
    public void run() {
        System.out.println("小米手机播放视频中");
    }
}

interface Music{
    void play();
}

class IPhonMusic implements Music{
    @Override
    public void play() {
        System.out.println("苹果手机播放音乐中");
    }
}

class MiPhoneMusic implements Music{
    @Override
    public void play() {
        System.out.println("小米手机播放音乐中");
    }
}
