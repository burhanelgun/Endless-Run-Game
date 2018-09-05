package game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RankTable extends State {
    protected RankTable(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }
}

//ilk başta oynarken oyun bittikten sonra direkman db ye kayıt yapılsın ve ardından o db ye göre rank gösterilsin altındada save to board butonu olsun ona tıkladığınd
// isim alsın ve ardından rank table a göndersin (bir sayfaya sığıcak kadar kişi arasında kendi rengi kırmızı olacak)

// artık save to board kalkıcak ve high score yaptıktan sonra direk db kaydı yapılıp rank table a gidilecek ve orda yeni sırası gösterecek.diğer sırasını silecek


