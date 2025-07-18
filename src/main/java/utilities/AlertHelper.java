package utilities;

import pages.InventoryPage;

public class AlertHelper {

    public static void handleAllAlerts(InventoryPage inventoryPage) {
        try {
            // Handle standard JavaScript alerts
            if (inventoryPage.isAlertPresent()) {
                String alertText = inventoryPage.getAlertText();

                if (alertText.contains("Google Password Manager")) {
                    inventoryPage.dismissAlert();
                    System.out.println("Dismissed standard Google alert");
                    Thread.sleep(500); // Small pause after dismissal
                    return;
                }
            }

            // Handle Google's iframe-based password alert
            if (inventoryPage.isGooglePasswordAlertPresent()) {
                inventoryPage.dismissGooglePasswordAlert();
                System.out.println("Dismissed iframe Google alert");
                Thread.sleep(500); // Small pause after dismissal
            }

        } catch (Exception e) {
            System.out.println("Error handling alerts: " + e.getMessage());
        }
    }
}