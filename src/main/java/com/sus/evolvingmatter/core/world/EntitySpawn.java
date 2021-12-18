package com.sus.evolvingmatter.core.world;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.core.init.EntityInit;
import com.sus.evolvingmatter.util.BiomeDictionarySupport;
import com.sus.evolvingmatter.util.ConfigurationHandler;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.feature.RuinedPortalFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = EvolvingMatter.MOD_ID)
public class EntitySpawn {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void addSpawn(BiomeLoadingEvent event) {
        if (event.getName() != null) {
            Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
            if (biome != null) {
                for (EntityDetails entityDetails : EntityDetails.values()){
                    ResourceKey<Biome> resourceKey = ResourceKey.create(ForgeRegistries.Keys.BIOMES, event.getName());
                    if (!entityDetails.biomeIncludList.isEmpty()) {
                        Set<BiomeDictionary.Type> biomeTypes = BiomeDictionary.getTypes(resourceKey);
                        if (biomeTypes.stream().noneMatch(entityDetails.biomeExcludeList::contains) && biomeTypes.stream().anyMatch(entityDetails.biomeIncludList::contains)) {
                            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(entityDetails.typeOfEntity, entityDetails.weight, entityDetails.min, entityDetails.max));
                        }
                    } else {
                        throw new IllegalArgumentException("Do not leave the BiomeDictionary type inclusion list empty. If you wish to disable spawning of an entity, set the weight to 0 instead.");
                    }
                }
            }
        }
    }

    public enum EntityDetails {
        soulghost(EntityInit.SOUL_GHOST.get(),
                Arrays.asList(BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MUSHROOM),
                Arrays.asList(BiomeDictionary.Type.END, RuinedPortalFeature.Type.NETHER),
                1,4,100),

        fiend(EntityInit.FIEND.get(),
                Arrays.asList(BiomeDictionary.Type.OVERWORLD),
                Arrays.asList(BiomeDictionary.Type.END, BiomeDictionary.Type.NETHER),
                1,4,100);


        private EntityType typeOfEntity;
        private List<BiomeDictionary.Type> biomeIncludList;
        private List<BiomeDictionary.Type> biomeExcludeList;
        private int min;
        private int max;
        private int weight;

        EntityDetails(EntityType entityType,@NotNull List biomeIncludList,@NotNull List biomeExcludeList, int minValue, int maxValue, int weight) {
            this.typeOfEntity = entityType;
            this.biomeIncludList = biomeIncludList;
            this.biomeExcludeList = biomeExcludeList;
            this.min=minValue;
            this.max=maxValue;
            this.weight=weight;
        }

    }
}
