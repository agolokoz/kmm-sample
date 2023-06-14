import UIKit
import shared

class MainScreenViewController: UIViewController {
    
    private let api: SpaceXApi = {
        let api = SpaceXApi()
        return api
    }()
    
    private var textLabel: UILabel?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .white
        
        let uiButton = UIButton()
        view.addSubview(uiButton)
        uiButton.translatesAutoresizingMaskIntoConstraints = false
        uiButton.topAnchor.constraint(equalTo: view.layoutMarginsGuide.topAnchor).isActive = true
        uiButton.leadingAnchor.constraint(equalTo: view.layoutMarginsGuide.leadingAnchor).isActive = true
        uiButton.setTitle("Load", for: .normal)
        uiButton.setTitleColor(.blue, for: .normal)
        uiButton.addTarget(self, action: #selector(didButtonClick), for: .touchUpInside)
        
        let textLabel = UILabel()
        view.addSubview(textLabel)
        textLabel.translatesAutoresizingMaskIntoConstraints = false
        textLabel.topAnchor.constraint(equalTo: uiButton.bottomAnchor).isActive = true
        textLabel.leadingAnchor.constraint(equalTo: uiButton.leadingAnchor).isActive = true
        textLabel.trailingAnchor.constraint(equalTo: view.layoutMarginsGuide.trailingAnchor).isActive = true
        textLabel.numberOfLines = 100
        textLabel.text = "Text"
        textLabel.textColor = .black
        self.textLabel = textLabel
    }
    
    @objc
    func didButtonClick(_ sender: AnyObject?) {
        Task {
            textLabel?.text = await getString()
        }
    }

    private func getString() async -> String {
        do {
            let launches = try await api.getAllLaunches()
            return launches.map { "\($0)" }.joined(separator: ",")
        } catch {
            return "Error"
        }
    }
}
