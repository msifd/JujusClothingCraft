/**
 *
 */
package clothingcraft.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.LinkedHashMap;

/**
 * List of sewing pattern items (helm, chest, pants, boots)
 * and their registrations into the world.
 * <p>
 * These are comprised of 6 different categories:
 * - Casual wear
 * - Career wear
 * - Formal wear
 * - Beach wear
 * - Sleep wear
 * - Costume wear
 * <p>
 * These are further broken down into 4 body purpose sub-types:
 * - Helm (head)
 * - Chest (top)
 * - Pants (leggings)
 * - Boots (shoes)
 *
 * @author JujuAwesomeBeans
 * @author NinjaSoldier40
 * @since 02/12/2017 11:00 AM (-7:00 GMT)
 */
public class ClothingCraftPatternItemList {

    /**
     * Linked Map containing all patterns by category and purpose.
     * The contents are accessed using (example):
     * <PRE>
     * Item patternCasualHelm = patternsItemMap.get(CATEGORY.casual).get(PURPOSE.helm);
     * </PRE>
     */
    public static LinkedHashMap<Category, LinkedHashMap<Purpose, Item>> patternsItemMap =
            new LinkedHashMap<Category, LinkedHashMap<Purpose, Item>>();

    ;

    public static void preInit() {
        for (Category enumCategory : Category.values()) {
            for (Purpose enumPurpose : Purpose.values()) {
                String unlocalizedName = "pattern_" + enumCategory.name() + "_" + enumPurpose.name();
                Item patternItem = (Item) new ItemPattern(unlocalizedName);
                GameRegistry.registerItem(patternItem, unlocalizedName);
                // populate the patternsItemMap
                if (patternsItemMap.get(enumCategory) == null) {
                    patternsItemMap.put(enumCategory, new LinkedHashMap<Purpose, Item>());
                }
                patternsItemMap.get(enumCategory).put(enumPurpose, patternItem);
            }
        }
    }

    ;

    public static void init() {
        for (Category enumCategory : Category.values()) {
            for (Purpose enumPurpose : Purpose.values()) {
                Item item = patternsItemMap.get(enumCategory).get(enumPurpose);
                // boots recipe
                ItemStack stack = new ItemStack(item, 1);
                GameRegistry.addRecipe(stack, getRecipe(enumCategory, enumPurpose));
            }
        }
    }

    /**
     * category > special ingredient:
     * --------   ------------------
     * beach    > Blocks.sand
     * career   > Items.stone_pickaxe
     * casual   > Blocks.grass
     * costume  > Blocks.pumpkin
     * formal   > Items.diamond
     * sleep    > Items.bed
     *
     * @param enumCategory
     * @param enumPurpose
     * @return
     */
    public static Object[] getRecipe(Category enumCategory, Purpose enumPurpose) {
        Object[] result = null;

        ItemStack specialIngredient = null;
        switch (enumCategory) {
            case beach:
                specialIngredient = new ItemStack(Blocks.sand, 1);
                break;
            case career:
                specialIngredient = new ItemStack(Items.stone_pickaxe, 1);
                break;
            case casual:
                specialIngredient = new ItemStack(Blocks.grass, 1);
                break;
            case costume:
                specialIngredient = new ItemStack(Blocks.pumpkin, 1);
                break;
            case formal:
                specialIngredient = new ItemStack(Items.diamond, 1);
                break;
            case sleep:
                specialIngredient = new ItemStack(Items.bed, 1);
                break;
            default:

        }

        switch (enumPurpose) {
            case helm:
                result = new Object[]{"012", "3XX", "XXX",
                        '0', new ItemStack(Items.paper, 1),
                        '1', new ItemStack(Items.leather_helmet, 1),
                        '2', new ItemStack(ClothingCraftToolsItemList.sewingScissors, 1),
                        '3', specialIngredient,
                };
                break;
            case chest:
                result = new Object[]{"012", "3XX", "XXX",
                        '0', new ItemStack(Items.paper, 1),
                        '1', new ItemStack(Items.leather_chestplate, 1),
                        '2', new ItemStack(ClothingCraftToolsItemList.sewingScissors, 1),
                        '3', specialIngredient,
                };
                break;
            case pants:
                result = new Object[]{"012", "3XX", "XXX",
                        '0', new ItemStack(Items.paper, 1),
                        '1', new ItemStack(Items.leather_leggings, 1),
                        '2', new ItemStack(ClothingCraftToolsItemList.sewingScissors, 1),
                        '3', specialIngredient,
                };
                break;
            case boots:
                result = new Object[]{"012", "3XX", "XXX",
                        '0', new ItemStack(Items.paper, 1),
                        '1', new ItemStack(Items.leather_boots, 1),
                        '2', new ItemStack(ClothingCraftToolsItemList.sewingScissors, 1),
                        '3', specialIngredient,
                };
                break;
            default:

        }
        return result;
    }

    public enum Category {beach, career, casual, costume, formal, sleep}

    public enum Purpose {helm, chest, pants, boots}
}
