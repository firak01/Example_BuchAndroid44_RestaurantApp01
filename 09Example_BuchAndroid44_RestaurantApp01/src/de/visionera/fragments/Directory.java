/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.visionera.fragments;

import android.content.Context;


public class Directory {
    private static DirectoryCategory[] mCategories;

    public static void initializeDirectory(Context ctx) {
      mCategories = new DirectoryCategory[] {    		  
                new DirectoryCategory(ctx.getString(R.string.kat_getraenke), new DirectoryEntry[] {
                        new DirectoryEntry(ctx.getString(R.string.item_latte), R.drawable.latte),
                        new DirectoryEntry(ctx.getString(R.string.item_saft), R.drawable.saft),
                        new DirectoryEntry(ctx.getString(R.string.item_bier), R.drawable.bier)}),                        
                new DirectoryCategory(ctx.getString(R.string.kat_vorspeisen), new DirectoryEntry[] {
                        new DirectoryEntry(ctx.getString(R.string.item_crostini), R.drawable.crostini),
                        new DirectoryEntry(ctx.getString(R.string.item_pastete), R.drawable.pastete),
                        new DirectoryEntry(ctx.getString(R.string.item_salat), R.drawable.salat)}),
                new DirectoryCategory(ctx.getString(R.string.kat_hauptgang), new DirectoryEntry[] {
                        new DirectoryEntry(ctx.getString(R.string.item_fisch), R.drawable.fisch),
                        new DirectoryEntry(ctx.getString(R.string.item_filet), R.drawable.schweinefilet),
                        new DirectoryEntry(ctx.getString(R.string.item_pizza), R.drawable.pizza)}),
                 };
    }

    public static int getCategoryCount() {
        return mCategories.length;
    }

    public static DirectoryCategory getCategory(int i) {
        return mCategories[i];
    }
}
