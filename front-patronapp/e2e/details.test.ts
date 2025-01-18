const { test, expect } = require('@playwright/test');

test('comprobar texto después de hacer clic en evento', async ({ page }) => {
    await page.goto('http://localhost:3000/village/1');
    await page.click('div.rbc-event-content[title="Concierto de Folk"]');
    const texto = await page.locator('text=Espectáculo musical al aire libre con artistas locales.');
    await expect(texto).toBeVisible();
});