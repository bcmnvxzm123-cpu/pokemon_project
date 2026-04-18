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

            // 手持ちポケモン
            int myPokemonNum = 0;       // 自分のポケモンの[No.]     を表す配列番号
            String myPokemonName = "";       // 自分のポケモンの[名前]     を代入する変数を定義
            int myPokemonHP = 0;        // 自分のポケモンの[HP]       を表す配列番号
            int myPokemonAttack = 0;    // 自分のポケモンの[攻撃力]    を表す配列番号
            int myPokemonSpeed = 0;     // 自分のポケモンの[スピード]   を表す配列番号

            // 野生ポケモン
            String ePokemonName = "";  // 自分のポケモンの[名前]     を代入する変数を定義
            int ePokemonNum = 0;       // 野生のポケモンの[No]      を表す配列番号
            int ePokemonHP = 0;         // 野生のポケモンの[HP]     　 を表す配列番号
            int ePokemonAttack = 0;     // 野生のポケモンの[攻撃力]     を表す配列番号
            int ePokemonSpeed = 0;      // 野生のポケモンの[スピード]    を表す配列番号

            //↓まずはポケモンにステータスを設定

            String[] pokemonName = {"コダック", "ピカチュウ", "イーブイ", "ヒポポタス"};

            int[][] states = {// ポケモンNo、HP、攻撃力、スピード
                    {0,20,15,20},    // コダック[0]
                    {1,30,30,40},    // ピカチュウ[1]
                    {2,15,30,25},    // イーブイ[2]
                    {3,9,9,9}       // ヒポポタス[3]
            };

            System.out.println();// 改行のためだけに記載

            //自分の手持ちポケモンをランダムで選択
            myPokemonNum = rnd.nextInt(pokemonName.length);// 自分の手持ちポケモン決定「myPokemonName」に自分のポケモンを表す配列番号が代入された状態。
            System.out.println("自分の手持ちポケモンは" + pokemonName[myPokemonNum] + "だ！\nよろしくね！" + pokemonName[myPokemonNum] + "！");
            
            for (int h = 0; h < pokemonName.length ; h++ ){// 該当する番号を検索して見つけたらステータスを代入して終了する処理
                if(h == myPokemonNum){

                    myPokemonName = pokemonName[h];  // ポケモンNo
                    myPokemonHP = states[h][1];      // HP
                    myPokemonAttack = states[h][2];  // 攻撃力
                    myPokemonSpeed = states[h][3];   // スピード
                    break;
                }
            }// ←どのポケモンと戦うことになったのか。名前やステータスがePokemonNameやePokemonSpeedに代入された状態

            // 草むらを歩くためいったん出す。
            while(myPokemonHP >= 0) {// 手持ちのポケモンが「ひんし」になるまで続けられる

                int encodingPattern = rnd.nextInt(100) +1;// whileの外でやりたくないけどエラーでる

                // ここにコマンド選択のコードいるくね？
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
                        System.out.println("キズぐすりをひろった！" + myPokemonName + "のHPを10回復した!");
                        myPokemonHP += 10; 
                        continue;
                    }
                    else{
                        System.out.println("ッ!!!");
                        ePokemonNum = rnd.nextInt(pokemonName.length);
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


                for (int i = 0; i < pokemonName.length ; i++ ){// 該当する番号を検索して見つけたらステータスを代入して終了する処理
                    if(i == ePokemonNum){
                        System.out.println(pokemonName[i] + "がとびだしてきた！");

                        ePokemonName = pokemonName[i];  // ポケモンNo
                        ePokemonHP = states[i][1];      // HP
                        ePokemonAttack = states[i][2];  // 攻撃力
                        ePokemonSpeed = states[i][3];   // スピード
                        break;
                    }
                }// ←どのポケモンと戦うことになったのか。名前やステータスがePokemonNameやePokemonSpeedに代入された状態

                // 戦闘ループ（逃げるか。死ぬか。）
                while (!endSelect) {// 終了するを選択するとendSelectのbooleanの旗が立つ。そしてこのループから抜ける。
                    // 野生ポケモンと手持ちポケモンのスピード勝負
                    if(myPokemonSpeed > ePokemonSpeed + 1){     // 自分が早い
                        firstAttack = true;
                    }
                    else if(myPokemonSpeed == ePokemonSpeed){   // 相手が早い
                        // firstAttack = false;// 記入の必要はないが可読性向上のため記載。
                    }
                    else {                                      // 同じスピード
                        int rndFirst = rnd.nextInt(2);// 0か1かを生成。1/2でtrueに入ってif文を抜ける
                        if (rndFirst == 0){
                            firstAttack = true;
                        }
                    }
                    // 野生ポケモンと手持ちポケモンのスピード勝負
                    // boolean firstAttackがスピード勝負の結果をここで持ってる

                    // 持ってるbooleanの旗↓
                    // boolean Win
                    // boolean firstAttack
                    // boolean endSelect
                    // boolean lose

                    boolean commandOk = false;
                    // コマンド選択
                    while (!commandOk) {// コマンド選択を繰り返させるためだけのwhile

                        System.out.println();
                        System.out.println("どうしますか？");
                        System.out.println("1:攻撃\t" + "2:逃げる");
                        int command = scn.nextInt();

                        if (command == 1) {
                            System.out.println("攻撃を選んだ");
                            System.out.println(ePokemonName + "に" + myPokemonAttack + "のダメージ！");
                            ePokemonHP -= myPokemonAttack;
                        }
                        else if(command == 2){
                            System.out.println(ePokemonName + "から逃げ出した");
                            break;// 「冒険を続ける」のwhileから抜ける
                        }
                        else {
                            System.out.println("エラー\tもう一度入力してください");
                            continue;
                        }
                    }
                    endSelect = true;
                }
                // 戦闘処理

                // 戦闘処理

                // 倒せたのか負けたのか
                if (myPokemonHP <= 0) {
                    System.out.println("手持ちのポケモンがいなくなった。。。");
                    System.out.println("目の前がまっくらになった。。。。");
                    Win = false;
                    break;
                }

                // 冒険を続けるのか
                System.out.println("冒険を続けますか？\n1:攻撃\t2:逃げる");
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
