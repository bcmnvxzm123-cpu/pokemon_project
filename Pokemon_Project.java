import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

    public class Pokemon_Project{
        public static void main(String[] args){

            Random rnd = new Random();
            Scanner scn = new Scanner(System.in);

            int winCounter = 0;         // たおしたポケモンのカウンター
            boolean endSelect = false;  // 自分で帰ることを選択した際にTrue
            boolean Win = false;        // 野生のポケモンに勝った場合に上げる旗。
            boolean lose = false;       // ポケモンとの戦いで自分の手持ちポケモンのHPが０になったときにTrue
            boolean firstAttack = false;// 自分のポケモンが早ければTrue
            String action = "";

            //↓まずはArrayクラスでポケモンの情報をメソッドに追加
            ArrayList<Pokemon> pokemons = new ArrayList<>();// ArrayListクラスの<Pokemon>というリストをnewして
            pokemons.add(new Pokemon("コダック",20,15,20));// <0>ポケモンのリストに順番にaddして（名前、HP、攻撃、スピード）
            pokemons.add(new Pokemon("ピカチュウ",30,30,40));// <1>ポケモンのリストに順番にaddして（名前、HP、攻撃、スピード）
            pokemons.add(new Pokemon("イーブイ",15,30,25));
            pokemons.add(new Pokemon("ヒポポタス",9,9,9));


            int index = rnd.nextInt(pokemons.size());
            Pokemon myPokemon = pokemons.get(index);
            System.out.println(myPokemon.name);

            System.out.println();// 改行のためだけに記載

            //自分の手持ちポケモンをランダムで選択
            System.out.println("自分の手持ちポケモンは" + myPokemon.name + "だ！");
            System.out.println("よろしくね！" + myPokemon.name + "！");

            while(myPokemon.hp > 0) {// 手持ちのポケモンが「ひんし」になるまで続けられる

                int encodingPattern = rnd.nextInt(100) +1;// whileの外でやりたくないけどエラーでる

                // 草むらを歩きますか？
                System.out.println("草むらを歩きますか？");
                System.out.println("1:はい  2:いいえ");
                int walk = scn.nextInt();

                if (walk == 1) {                // １歩進むを選択
                    // エンカウントイベント発生。⓵何もいなかった60%⓶キズぐすりをひろう10%⓷ポケモンと遭遇30%
                    System.out.println("草むらを１歩進んだ");
                    if(encodingPattern >= 71){// 30%の確率で何もいない。
                        System.out.println("しかしなにもいなかった");
                        continue;
                    }
                    else if (encodingPattern <= 10) {// 10%の確率でキズぐすりを手に入れる
                        System.out.println("キズぐすりをひろった！" + "自分の" + myPokemon.name + "のHPを10回復した!");
                        myPokemon.hp += 10; 
                        continue;
                    }
                    else{
                        System.out.println("ッ!!!");
                    }
                }
                else if (walk == 2) {           // 冒険を終了し、ヒポポタウンに戻る。
                    System.out.println("終了します。");
                    endSelect = true;
                    break;
                }
                else{                           // もう一度入力を求める
                    System.out.println("正しい数字を入力してね！");
                    continue;
                }

                // ここで野生のポケモンを決定。
                int eindex = rnd.nextInt(pokemons.size());
                Pokemon ePokemon = pokemons.get(eindex);
                System.out.println(ePokemon.name + "がとびだしてきた！");

                firstAttack = false;
                // 野生ポケモンと手持ちポケモンのスピード勝負
                if(myPokemon.speed > ePokemon.speed){     // 自分が早い
                    firstAttack = true;
                }
                else if(myPokemon.speed < ePokemon.speed){   // 相手が早い
                    // firstAttack = false;// 記入の必要はないが可読性向上のため記載。
                }
                else {                                      // 同じスピード
                    int rndFirst = rnd.nextInt(2);// 0か1かを生成。1/2でtrueに入ってif文を抜ける
                    if (rndFirst == 0){
                        firstAttack = true;
                    }
                }
                // 野生ポケモンと手持ちポケモンのスピード勝負

                    // 持ってるbooleanの旗↓
                    // boolean Win
                    // boolean firstAttack
                    // boolean endSelect
                    // boolean lose

                // 同じポケモンとの戦闘ループ（逃げるか。死ぬか。）
                while (!endSelect) {// 終了するを選択するとendSelectのbooleanの旗が立つ。そしてこのループから抜ける。
                    boolean commandOk = false;
                    // コマンド選択
                    while (!commandOk) {// コマンド選択を繰り返させるためだけのwhile
                        System.out.println();
                        System.out.println("どうしますか？");
                        System.out.println("1:攻撃\t" + "2:逃げる");
                        int command = scn.nextInt();

                        if (command == 1) {
                            commandOk = true;
                            System.out.println("攻撃を選んだ！");
                            action = "attack";
                            break;
                        }
                        else if(command == 2){
                            commandOk = true;
                            System.out.println("逃げるを選択した！");
                            action = "escape";
                            break;
                        }
                        else {
                            System.out.println("エラー\tもう一度入力してください");
                            continue;
                        }
                    }

                    // 戦闘処理　⓵自分→相手　⓶相手→自分　⓷逃げられなかった（相手のみ）　⓸逃げられた
                    if (firstAttack && action.equals("attack")) {// ⓵自分→相手
                        System.out.println("自分の" + myPokemon.name + "の攻撃！");
                        System.out.println(ePokemon.name + "に" + myPokemon.attack + "のダメージ！");
                        ePokemon.hp -= myPokemon.attack;
                            if (ePokemon.hp <= 0) {// 野生のポケモンを倒していれば
                                System.out.println(ePokemon.name + "をたおした！");
                                Win = true;
                                winCounter ++;
                                break;
                            }
                        System.out.println(ePokemon.name + "の攻撃！");
                        System.out.println("自分の" + myPokemon.name + "に" + ePokemon.attack + "のダメージ！");
                        myPokemon.hp -= ePokemon.attack;
                        if (myPokemon.hp <= 0) {
                            System.out.println("自分の" + myPokemon.name + "は倒れた");
                            endSelect = true;
                        }
                    }
                    else if(!firstAttack && action.equals("attack")){// ⓶相手→自分
                        System.out.println(ePokemon.name + "の攻撃！");
                        System.out.println("自分の" + myPokemon.name + "に" + ePokemon.attack + "のダメージ！");
                        myPokemon.hp -= ePokemon.attack;
                        if (myPokemon.hp <= 0) {
                            System.out.println("自分の" + myPokemon.name + "は倒れた");
                            endSelect = true;
                            break;
                        }// 次は自分の攻撃↓
                        System.out.println("自分の" + myPokemon.name + "の攻撃！");
                        System.out.println(ePokemon.name + "に" + myPokemon.attack + "のダメージ！");
                        ePokemon.hp -= myPokemon.attack;
                        if (ePokemon.hp <= 0) {
                            System.out.println(ePokemon.name + "をたおした！");
                            Win = true;
                            winCounter ++;
                        }
                    }
                    else if(action.equals("escape")){// 逃げるを選択（booleanでcanEscapeがいるかも）
                        if(firstAttack){// 逃げ出せた
                            System.out.println(ePokemon.name + "から逃げだした！");
                            break;
                        }
                        else {// 逃げ出せなかった
                            System.out.println("しかしまわりこまれてしまった！");
                            System.out.println(ePokemon.name + "の攻撃！");
                            System.out.println("自分の" + myPokemon.name + "に" + ePokemon.attack + "のダメージ！");
                            myPokemon.hp -= ePokemon.attack;
                            if (myPokemon.hp <= 0) {
                                System.out.println("自分の" + myPokemon.name + "は倒れた");
                                endSelect = true;
                            }
                        }
                    }
                }
                // 戦闘処理
                // 倒せたのか負けたのか
                if (myPokemon.hp <= 0) {
                    System.out.println("手持ちのポケモンがいなくなった。。。");
                    System.out.println("目の前がまっくらになった。。。。");
                    Win = false;
                    break;
                }

                // 冒険を続けるのか
                System.out.println("冒険を続けますか？\n1:Yes\t2:No");
                int endSelect1 = scn.nextInt();
                if(endSelect1 == 1){
                    System.out.println("冒険を続けるを選択しました");
                }
                else {
                    System.out.println("冒険を続けないを選択しました");
                    break;
                }//
            }// ←草むらを歩く終了
            // 最終ステータス表示
            System.out.println("↓最終ステータス↓");
            System.out.println("たおしたポケモンの数\t" + winCounter + "匹");
            System.out.println("そらをとぶ！\nヒポポタウンにかえった！");
            System.out.println("～END～");
    }

}

class Pokemon {// ここに全ポケモンが絶対持ってるステータスがある。（ポケモンの設計部品の倉庫）
    String name;// 名前が必要だね
    int hp;// HPが必要だね
    int attack;//攻撃力が必要だね 
    int speed;// スピードが必要だね

    Pokemon(String name,int hp,int attack,int speed){// そんなポケモン
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.speed = speed;
    }
}

