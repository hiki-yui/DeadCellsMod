package deadCellsMod.cn.infinite.stsmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class DeadCellsCard extends CustomCard {

    public DeadCellsCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    public DeadCellsCard(String id, String name, RegionName img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    @Override
    public abstract void upgrade();

    @Override
    public abstract void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster);

    @Override
    public AbstractCard makeCopy() {
        AbstractCard card = new Strike_king();
        try {
             card = this.getClass().newInstance();
            if (this.upgraded){
                card.upgrade();
            }

        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return card;
    }
}
