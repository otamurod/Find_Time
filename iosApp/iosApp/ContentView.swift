import shared
import SwiftUI

struct ContentView: View {
    @StateObject private var timezoneItems = TimezoneItems()

    var body: some View {
        TabView {
            TimezoneView()
                .tabItem {
                    Label("Time Zones", systemImage: "network")
                }
            FindMeeting()
                .tabItem {
                    Label("Find Meeting", systemImage: "clock")
                }
        }
        .accentColor(Color.white)
        .environmentObject(timezoneItems)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
