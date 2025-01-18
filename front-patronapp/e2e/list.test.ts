import { test, expect } from '@playwright/test';

test('search for Castronuño and check result', async ({ page }) => {
    await page.goto('http://localhost:3000/province/49');
    const searchBar = page.locator('#search-bar');
    await searchBar.fill('Castronuño');
    const searchButton = page.locator('button:has-text("Buscar")');
    await searchButton.click();
    const button = page.getByRole('button', { name: 'Castronuño Castronuño 2025-09' });
    await expect(button).toBeVisible();
});

test('filter by date and check result', async ({ page }) => {
    await page.goto('http://localhost:3000/province/49');
    const startDateFilter = page.locator('#date-filter').first();
    await startDateFilter.fill('2025-09-19');
    const endDateFilter = page.locator('#date-filter').nth(1);
    await endDateFilter.fill('2025-09-23');
    const searchButton = page.locator('button:has-text("Buscar")');
    await searchButton.click();
    const button = page.getByRole('button', { name: 'Castronuño Castronuño 2025-09' });
    await expect(button).toBeVisible();
});