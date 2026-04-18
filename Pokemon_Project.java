import java.util.Random;
import java.util.Scanner;

    public class Pokemon_States {
        public static void main(String[] args){

            Random rnd = new Random();
            Scanner scn = new Scanner(System.in);

            int winCounter = 0;         // たおしたポケモンのカウンター
            boolean endSelect = false;  // 自分で帰ることを選択した際にTrue
            boolean lose = false;       // ポケモンとの戦いで自分の手持ちポケモンのHPが０になったときにTrue
            boolean firstAttack = false;// 自分のポケモンが早ければTrue

            // 手持ちポケモン
            int myPokemonName = 0;      // 自分のポケモンの[名前]     を表す配列番号
            int myPokemonHP = 0;        // 自分のポケモンの[HP]       を表す配列番号
            int myPokemonAttack = 0;    // 自分のポケモンの[攻撃力]    を表す配列番号
            int myPokemonSpeed = 0;     // 自分のポケモンの[スピード]   を表す配列番号

            // 野生ポケモン
            String ePokemonName = "";
            int ePokemonNum = 0;       // 野生のポケモンの[名前]      を表す配列番号
            int ePokemonHP = 0;         // 野生のポケモンの[HP]     　 を表す配列番号
            int ePokemonAttack = 0;     // 野生のポケモンの[攻撃力]     を表す配列番号
            int ePokemonSpeed = 0;      // 野生のポケモンの[スピード]    を表す配列番号


            //↓まずはポケモンにステータスを設定

            String[] pokemon = {"コダック", "ピカチュウ", "イーブイ", "ヒポポタス"};

            int[][] states = {// ポケモンNo、HP、攻撃力、スピード
                    {0,10,10,10},    // コダック[0]
                    {1,30,30,40},    // ピカチュウ[1]
                    {2,31,30,30},    // イーブイ[2]
                    {3,9,9,9}       // ヒポポタス[3]
            };

            System.out.println();// 改行のためだけに記載

            //自分の手持ちポケモンをランダムで選択
            myPokemonName = rnd.nextInt(pokemon.length);// 自分の手持ちポケモン決定「myPokemonName」に自分のポケモンを表す配列番号が代入された状態。
            System.out.println("自分の手持ちポケモンは" + pokemon[myPokemonName] + "だ！\nよろしくね！" + pokemon[myPokemonName] + "！");

            // 草むらを歩く
            while(myPokemonHP <= 0) {// 手持ちのポケモンが「ひんし」になるまで続けられる

                boolean Win = false;

                // エンカウントイベント発生
                int encodingPattern = rnd.nextInt(pokemon.length);

                for ( int i = 0 ; i < pokemon.length ; i++ ){
                    if (encodingPattern == i) {// 該当する番号を検索して見つけたらステータスを代入して終了する処理
                        System.out.println(pokemon[i] + "がとびだしてきた！");

                        ePokemonName = pokemon[i];// int型→String型へ
                        ePokemonHP = states[i][1];
                        ePokemonAttack = states[i][2];
                        ePokemonSpeed = states[i][3];
                        break;
                    }
                    else if(i > pokemon.length) {
                        System.out.println("キズぐすりをひろった！");
                        myPokemonHP = states[myPokemonName][0];
                    }// ←どのポケモンと戦うことになったのか。名前やステータスがePokemonNameやePokemonSpeedに代入された状態
                }

                // 戦闘ループ（逃げるか。死ぬか。）
                while (!endSelect) {
                    // 野生ポケモンと手持ちポケモンのスピード勝負
                    if(myPokemonSpeed > ePokemonSpeed + 1){// 自分が早い
                        firstAttack = true;
                    }
                    else if(myPokemonSpeed == ePokemonSpeed){// 相手が早い
                        // firstAttack = false;// 記入の必要はないが可読性向上のため記載。
                    }
                    else {// 同じスピード
                        int rndFirst = rnd.nextInt(2);// 0か1かを生成。1/2でtrueに入ってif文を抜ける
                        if (rndFirst == 0){
                            firstAttack = true;
                        }
                    }// boolean firstAttackがスピード勝負の結果をここで持ってる

                    // 持ってるbooleanの旗↓
                    // boolean Win
                    // boolean firstAttack
                    // boolean endSelect
                    // boolean lose

                    boolean commandOk = false;
                    // コマンド選択
                    while (commandOk) {// コマンド選択を繰り返させるためだけのwhile

                        System.out.println();
                        System.out.println("どうしますか？");
                        System.out.println("1:攻撃\t" + "2:逃げる");
                        int command = scn.nextInt();

                        if (command == 1) {
                            System.out.println("攻撃を選んだ");
                            commandOk = true;
                            switch (){
                                case 1:
                                    System.out.println();

                                case 2:
                                    System.out.println();


                            }



                        }
                        else if(command == 2){
                            System.out.println(ePokemonName + "から逃げ出した");
                            commandOk = true;
                            break;// 「冒険を続ける」のwhileから抜ける
                        }
                        else {
                            System.out.println("エラー\tもう一度入力してください");
                            continue;
                        }
                    }

                    endSelect = true;
                }
                // 戦闘ループ

                // 戦闘処理
                for(int j = 0 ; j < 2 ; j++){

                }
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

